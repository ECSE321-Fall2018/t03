package ca.mcgill.ecse321.ridesharing;


@RestController
@SpringBootApplication
public class RidesharingApplication{
public static void main(String[] args) {
SpringApplication.run(RidesharingApplication.class, args);
    }

@RequestMapping("/")
public String greeting ()
{
return "Hello!";
    }
}