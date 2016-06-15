package demo.factory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

@Component
public class LazyContainersFactoryImpl implements LazyContainersFactory {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	/* (non-Javadoc)
	 * @see demo.factory.LazyContainersFactory#makeReadOnly(java.lang.Class)
	 */
	@Override
	public <T> JPAContainer<T> makeReadOnly(Class<T> entityClass) {
		return JPAContainerFactory.makeReadOnly(entityClass, entityManager);
	}
	/* (non-Javadoc)
	 * @see demo.factory.LazyContainersFactory#make(java.lang.Class)
	 */
	@Override
	public <T> JPAContainer<T> make(Class<T> entityClass) {
		return JPAContainerFactory.make(entityClass, entityManager);
	}
}
