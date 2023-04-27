import com.example.findadjacentwifi.WifiLoadService;

public class TestWifiService {
    public static void main(String[] args) {
        WifiLoadService service = new WifiLoadService();
        service.getWifiAsJSON(1, 2);
        service.parseJSON();
    }
}
