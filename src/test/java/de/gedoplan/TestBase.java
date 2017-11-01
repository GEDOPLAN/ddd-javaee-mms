package de.gedoplan;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.InjectionTarget;

import org.apache.deltaspike.cdise.api.ContextControl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class TestBase {

  private static SeContainerInitializer seContainerInitializer;;
  protected static SeContainer container;

  @BeforeClass
  public static void startCdiContainer() {
    System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");

    if (seContainerInitializer == null) {
      seContainerInitializer = SeContainerInitializer.newInstance();

      // TODO Could be done for each class, but OWB fails if a new container is started per test
      container = seContainerInitializer.initialize();
    }
  }

  @Before
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void startRequestContextAndHandleInjectsInTestClass() {
    ContextControl contextControl = container.select(ContextControl.class).get();
    contextControl.startContext(RequestScoped.class);

    BeanManager beanManager = container.getBeanManager();

    CreationalContext creationalContext = beanManager.createCreationalContext(null);

    AnnotatedType annotatedType = beanManager.createAnnotatedType(this.getClass());
    InjectionTarget injectionTarget = beanManager.createInjectionTarget(annotatedType);
    injectionTarget.inject(this, creationalContext);
  }

  @After
  public void stopRequestContext() {
    ContextControl contextControl = container.select(ContextControl.class).get();
    contextControl.stopContext(RequestScoped.class);
  }

  // TODO Could be done for each class, but OWB fails if a new container is started per test
  // @AfterClass
  // public static void stopCdiContainer() {
  // if (container != null) {
  // container.close();
  // }
  // }

}
