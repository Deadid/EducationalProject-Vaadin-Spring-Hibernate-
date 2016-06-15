package demo.factory;

import com.vaadin.addon.jpacontainer.JPAContainer;

/**
 * Factory that creates different JPAContainers for lazy loading
 * @author smakhov
 *
 */
public interface LazyContainersFactory {

	<T> JPAContainer<T> makeReadOnly(Class<T> entityClass);

	<T> JPAContainer<T> make(Class<T> entityClass);

}