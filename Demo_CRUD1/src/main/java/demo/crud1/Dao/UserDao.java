package demo.crud1.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import demo.crud1.model.User;
import demo.crud1.utils.JpaUtils;

public class UserDao {
	private EntityManager em;
	private EntityTransaction et;

	public UserDao() {
		this.em = JpaUtils.getEntityManager();
		this.et = this.em.getTransaction();

	}

	public List<User> getAll() {
		TypedQuery<User> query = this.em.createNamedQuery("User.findAll", User.class);
		List<User> list = query.getResultList();
		return list;
	}

	public User insert(User us) {
		try {
			this.et.begin();
			em.persist(us);
			this.et.commit();
			return us;
		} catch (Exception e) {
			e.printStackTrace();
			this.et.rollback();
		}
		return null;
	}

	public void update(User us) {
		try {
			this.et.begin();
			em.merge(us);
			this.et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.et.rollback();
		}
	}

	public void delete(User us) {
		try {
			this.et.begin();
			em.remove(us);
			this.et.commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.et.rollback();
		}
	}

	public User findByID(String id) {
		User us = em.find(User.class, id);
		return us;
	}
}
