@RestController
@SpringBootApplication
public class DemoApplication {

    @GetMapping("/")
    public String home() {
        return "Java App deployed via Jenkins & Kubernetes 🚀";
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

