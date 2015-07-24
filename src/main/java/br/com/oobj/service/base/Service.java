package br.com.oobj.service.base;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.oobj.model.base.EntidadeOobj;
import br.com.oobj.util.JPAUtil;

public abstract class Service<T extends EntidadeOobj> {

	protected EntityManager entityManager;
	
	protected abstract DAO<T> getDAO();

	private Class<T> classeDaEntidade;

	public T salvar(T entidade) {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		try {
			entidade = getDAO().salvar(entidade);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
		return entidade;
	}

	public void excluir(T entidade) {
		if (!entityManager.getTransaction().isActive()) {
			entityManager.getTransaction().begin();
		}
		
		try {
			getDAO().excluir(entidade);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public T consultarPorId(Integer codigo) {
		try {
			return (T) getDAO().consultarPorId(codigo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<T> consultarTodos() {
		try {
			return (List<T>) getDAO().consultarTodos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean isNotNull(Object objeto) {
		return objeto != null;
	}

	public boolean isNull(Object objeto) {
		return objeto == null;
	}

	private static Class<?> getClasseGenerica(Class<?> classe) {
		if (classe == null)
			return null;

		if (classe.getGenericSuperclass() instanceof ParameterizedType)
			return (Class<?>) ((ParameterizedType) classe.getGenericSuperclass()).getActualTypeArguments()[0];

		return null;
	}

	@SuppressWarnings("unused")
	private Class<T> getClasseDaEntidade() {
		return classeDaEntidade;
	}

	private void setClasseDaEntidade(Class<T> classeDaEntidade) {
		this.classeDaEntidade = classeDaEntidade;
	}
	
	@SuppressWarnings("unchecked")
	public Service() {
		entityManager = JPAUtil.createEntityManager();
		setClasseDaEntidade((Class<T>) getClasseGenerica(this.getClass()));
	}
}
