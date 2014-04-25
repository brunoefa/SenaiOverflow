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
import dao.RespostaDAO;
import entity.Pergunta;
import entity.Resposta;

@WebServlet("/resposta")
public class RespostaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RespostaController() {
        super();
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		if (("responder").equals(acao)){
			responder(request, response);
		}else if (("qualificar").equals(acao)){
			qualificar(request, response);
		}else {
			listar(request, response);
		}
	}

    private void qualificar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String parameterId = request.getParameter("id");
    	String pontuacao = request.getParameter("pontuacao");
		Integer id = 0;
		try {
			id = Integer.parseInt(parameterId);
			RespostaDAO dao = new RespostaDAO();
			
			Resposta resposta = new Resposta();
			resposta = dao.getResposta(id);
			
			if ("positivo".equals(pontuacao)) {
				resposta.setPositivo(resposta.getPositivo() + 1);
			} else if ("negativo".equals(pontuacao)) {
				resposta.setNegativo(resposta.getNegativo() + 1);
			}
			
			dao.qualificar(resposta);
			
			request.setAttribute("id", String.valueOf(resposta.getPerguntaId()));
			listar(request, response);
		} catch (NumberFormatException e) { }
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameterId = request.getParameter("id");
		if (parameterId == null) {
			parameterId = (String) request.getAttribute("id");
		}
		Integer id = 0;
		try {
			id = Integer.parseInt(parameterId);
		} catch (NumberFormatException e) { }
		
		RespostaDAO dao = new RespostaDAO();
		List<Resposta> lista = dao.getLista(id);
		
		PerguntaDAO perguntaDAO = new PerguntaDAO();
		Pergunta pergunta = perguntaDAO.getPergunta(id);

		request.setAttribute("lista", lista);
		request.setAttribute("pergunta", pergunta);
		RequestDispatcher rd = request.getRequestDispatcher("respostas.jsp");
		rd.forward(request, response);
	}

	private void responder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		Resposta resposta = new Resposta();
		resposta.setResposta(request.getParameter("resposta"));
		resposta.setAutor(request.getParameter("autor"));
		
		try {
			Integer id = Integer.parseInt(request.getParameter("idpergunta"));
			resposta.setPerguntaId(id);
			request.setAttribute("id", String.valueOf(id));
		} catch (NumberFormatException e) { }

		RespostaDAO respostaDAO = new RespostaDAO();
		respostaDAO.adiciona(resposta);
		
		listar(request, response);
	}
}
