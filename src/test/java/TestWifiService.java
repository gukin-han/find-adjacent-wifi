import com.example.findadjacentwifi.WifiLoader;

public class TestWifiService {
    public static void main(String[] args) {
        WifiLoader service = new WifiLoader();
        service.getWifiAsJSON(1, 2);
        service.parseJSON();
    }
}
