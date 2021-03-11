package servlet;

import static org.neo4j.driver.Values.*;

import java.io.IOException;
// List<T>
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Neo4j パッケージ
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Values;

/**
 * Servlet implementation class MovieController
 */
@WebServlet("/MovieController")
public class MovieController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Neo4j ドライバ
	// Neo4jサーバとのセッション作成に使用
	Driver driver;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovieController() {
        super();
        // TODO Auto-generated constructor stub

        // Neo4j サーバ認証
        driver = GraphDatabase.driver("neo4j://localhost:7687", AuthTokens.basic("neo4j", "perfume-easy-coral-amber-verona-4348"));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		// 呼び出し元JSPからデータの受け取り
		request.setCharacterEncoding("UTF-8");
		String searchVal = request.getParameter("searchVal");

		// クエリ取得結果格納先
		List<String> label = new ArrayList<String>();
		String name = "";
		int born = 0;

		// Neo4j 接続
		try (Session session = driver.session())
		{
			// Cypher クエリ実行
			Result result = session.run(
					"MATCH (n:Person) WHERE n.name = $searchName RETURN labels(n) AS label, n.name AS name, n.born as born",
					parameters("searchName", searchVal));
			// 取得レコード編集処理
			while (result.hasNext())
			{
				Record record = result.next();
				label = record.get("label").asList(Values.ofToString());
				name = record.get("name").asString();
				born = record.get("born").asInt();
			}
		}
		finally
		{
			// Neo4j ドライバクローズ
			driver.close();
		}

//		response.getWriter().append(label + ", " + name + ", " + born);

		// 呼び出し先JSPに渡すデータセット
		request.setAttribute("resultStr", String.join(",", label) + ", " + name + ", " + String.valueOf(born));

		// result.jsp にページ遷移
		RequestDispatcher dispatch = request.getRequestDispatcher("SearchResult.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
