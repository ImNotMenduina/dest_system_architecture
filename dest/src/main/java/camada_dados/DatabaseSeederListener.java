package camada_dados;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class DatabaseSeederListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Database.getInstance();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Aqui você pode fechar conexões ou realizar outras tarefas de limpeza
	}
}