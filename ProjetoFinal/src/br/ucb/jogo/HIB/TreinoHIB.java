package br.ucb.jogo.HIB;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.ucb.jogo.bean.PersonagenHasTreino;
import br.ucb.jogo.bean.Treino;
import br.ucb.jogo.interfaces.HIB;



public class TreinoHIB implements HIB<Treino>{

	@Override
	public boolean save(Treino t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Treino> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Treino.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Treino t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Treino findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Treino u = (Treino) session.get(Treino.class, id);
			return u;
		} finally {
			session.close();
		}
	}

	public int findByIdMax(int idPerson){


		Session session = HibernateUtil.getSession();
		Criteria criteria;
		try {
			criteria = session.createCriteria(Treino.class);
			criteria.setProjection(Projections.projectionList().add(Projections.max("idTreino"))
					).list();
			criteria.add(Restrictions.eq("personagens.get(idPerson-1).idPersonagens",idPerson));
			@SuppressWarnings("unchecked")
			ArrayList<Treino> a = (ArrayList<Treino>) criteria.list();
			for (Treino treino : a) {
				return treino.getIdTreino();
			}
		} finally {
			session.close();
		}
		return 0;
	}


}
