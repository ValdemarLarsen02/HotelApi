package app;

import app.routes.Routes;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.router.apiBuilder(Routes::setupRoutes);
        });

        app.start(7000);
        System.out.println("Server running on http://localhost:7000");
    }
}
