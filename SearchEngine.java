import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class SearchEngine implements URLHandler {

    public static List<String> keywords = new ArrayList<>();

    @Override
    public String handleRequest(URI url)
    {
        if (url.getPath().equals("/"))
        {
            return "Please enter your keyword.";
        } else if (url.getPath().equals("/add"))
        {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s"))
            {
                keywords.add(parameters[1]);
                return String.format("Successfully stored keyword: %s",
                    parameters[1]);
            }
            return "404 not found!";
        } else if (url.getPath().equals("/search"))
        {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("s"))
            {
                String keyword = parameters[1];
                List<String> answer = new ArrayList<>();
                for (String s : keywords)
                {
                    if (s.contains(keyword)) answer.add(s);
                }
                return answer.toString();
            }

            return "404 not found!";
        } else
        {
            return "404 not found!";
        }
    }
    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngine());
    }
}