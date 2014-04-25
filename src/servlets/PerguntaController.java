package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PerguntaDAO;
import entity.Pergunta;

/**
 * Servlet implementation class PerguntaController
 */
@WebServlet("/pergunta")
public class PerguntaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PerguntaController() {
		super();

	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (("perguntar").equals(acao)) {
			perguntar(request, response);
		} else {
			listar(request, response);
		}
	}

	private void listar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PerguntaDAO dao = new PerguntaDAO();
		List<Pergunta> lista = dao.getLista();

		request.setAttribute("lista", lista);
		RequestDispatcher rd = request.getRequestDispatcher("perguntas.jsp");
		rd.forward(request, response);
	}

	private void perguntar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Pergunta pergunta = new Pergunta();
		pergunta.setpergunta(request.getParameter("pergunta"));
		pergunta.setautor(request.getParameter("autor"));

		try {
			PerguntaDAO perguntaDAO = new PerguntaDAO();
			perguntaDAO.adiciona(pergunta);
		} catch (NumberFormatException e) {
			request.setAttribute("mensagem", "Post inválido");
		}

		listar(request, response);

	}
}
