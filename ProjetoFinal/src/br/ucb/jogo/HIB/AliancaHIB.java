package br.ucb.jogo.HIB;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.ucb.jogo.bean.Alianca;
import br.ucb.jogo.interfaces.HIB;

public class AliancaHIB implements HIB<Alianca>{

	
	@Override
	public boolean save(Alianca t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(t);
		transaction.commit();
		session.close();
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alianca> list() {
		Session session = HibernateUtil.getSession();
		try {
			return session.createCriteria(Alianca.class).list();
		} finally {
			session.close();
		}
	}

	@Override
	public boolean excluir(Alianca t) {
		Session session = HibernateUtil.getSession();
		Transaction transaction = session.beginTransaction();
		session.delete(t);
		transaction.commit();
		session.close();
		return false;
	}

	@Override
	public Alianca findTById(Integer id) {
		Session session = HibernateUtil.getSession();
		try {
			Alianca u = (Alianca) session.get(Alianca.class, id);
			return u;
		} finally {
			session.close();
		}
	}
}
