package service;

import java.util.ArrayList;
import java.util.List;

import dao.TripDAO;
import dto.QuestionDTO;



public class QnaService {
	TripDAO tripDAO;
	
	public QnaService() {
		tripDAO = new TripDAO();
	}
	
	List recursive(int pId, List list) {
		List resultList = new ArrayList();
		
		for(int i=0; i<list.size(); i++) {
			
			QuestionDTO dto = (QuestionDTO)list.get(i);
			if(dto.getQuestion_parentno() == pId) {
				resultList.add(dto);

				List tempList = recursive(dto.getQuestion_no(), list);
				resultList.add( tempList );
				
			}
		}
		
		return resultList;
	}
	
	public int getTotal() {
		return tripDAO.selectTotalQuestion();
	}
	
	public List<QuestionDTO> listArticles(int pageNum, int countPerPage){
		List<QuestionDTO> articlesList = tripDAO.selectAllQuestion(pageNum, countPerPage);
		
		return articlesList;
	}
	
	public void addArticle(QuestionDTO questionDTO) {
		tripDAO.insertNewQuestion(questionDTO);
	}
	public void addReply(QuestionDTO questionDTO) {
		tripDAO.insertNewQuestion(questionDTO);
	}
	
	
	
	public List<QuestionDTO> listQna(int question_no){
		List<QuestionDTO> QuestionList = tripDAO.selectQuestion(question_no);
		
		return QuestionList;
	}
}
