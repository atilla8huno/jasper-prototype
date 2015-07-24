package br.com.oobj.service.base;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import br.com.oobj.model.base.EntidadeOobj;

public abstract class DAO<T extends EntidadeOobj> {

	private Class<T> classeDaEntidade;

	protected EntityManager entityManager;

	protected T salvar(T entidade) throws Exception {
		return (T) entityManager.merge(entidade);
	}

	protected Criteria createCriteria() throws Exception {
		Session session = ((Session) entityManager.getDelegate());
		return session.createCriteria(getClasseDaEntidade());
	}

	protected DetachedCriteria createDetachedCriteria() throws Exception {
		return DetachedCriteria.forClass(getClasseDaEntidade());
	}

	protected DetachedCriteria createDetachedCriteria(Class<?> clazzEntidade) throws Exception {
		return DetachedCriteria.forClass(clazzEntidade);
	}

	protected Session getSession() {
		return ((Session) entityManager.getDelegate());
	}

	protected void excluir(T entidade) throws Exception {
		entityManager.remove(entidade);
	}

	protected T consultarPorId(Integer codigo) throws Exception {
		return (T) entityManager.find(getClasseDaEntidade(), codigo);
	}

	@SuppressWarnings("unchecked")
	protected List<T> consultarTodos() throws Exception {
		Criteria criteria = createCriteria();
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	protected boolean isTemAtributo(String nomeAtributo) {
		try {
			Field[] propriedades = getClasseDaEntidade().getDeclaredFields();
			for (Field propriedade : propriedades) {
				if (propriedade.getName().equals(nomeAtributo)) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	protected List<T> listar(Criteria criteria) {
		return criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	protected void addOrdemCriteria(Criteria criteria, Collection<String> ordens) {
		for (String item : ordens) {
			criteria.addOrder(Order.asc(item));
		}
	}

	/**
	 * Método construtor
	 */
	@SuppressWarnings("unchecked")
	public DAO(EntityManager entityManager) {
		setClasseDaEntidade((Class<T>) getClasseGenerica(this.getClass()));
		this.entityManager = entityManager;
	}

	private static Class<?> getClasseGenerica(Class<?> classe) {
		if (classe == null) {
			return null;
		} else if (classe.getGenericSuperclass() instanceof ParameterizedType) {
			return (Class<?>) ((ParameterizedType) classe.getGenericSuperclass()).getActualTypeArguments()[0];
		} else {
			return null;
		}
	}

	@SuppressWarnings("unused")
	private String getNomeDaEntidade() {
		return getClasseDaEntidade().getSimpleName();
	}

	protected Class<T> getClasseDaEntidade() {
		return classeDaEntidade;
	}

	private void setClasseDaEntidade(Class<T> classeDaEntidade) {
		this.classeDaEntidade = classeDaEntidade;
	}
}
