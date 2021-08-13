import cafeapi.CafeAPI;

public class Main {

    private static CafeAPI cafeAPI;

    public static void main(String[] args) {
        cafeAPI = new CafeAPI("beanbeanjuice", "password123", true);

        System.out.println(cafeAPI.users.getUsers().size());
    }

}
