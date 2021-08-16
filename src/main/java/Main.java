import cafeapi.CafeAPI;

public class Main {

    private static CafeAPI cafeAPI;

    public static void main(String[] args) {
        cafeAPI = new CafeAPI("beanbeanjuice", "password123", true);

        System.out.println(cafeAPI.users().getUsers().size());
//        System.out.println(cafeAPI.users().signUp("beanbeanjuice3", "password3").getData().get("message"));

        System.out.println("Getting User...");
//        System.out.println(cafeAPI.users().getUser(5).getUsername());
//        System.out.println(cafeAPI.users().deleteUser(5));
        System.out.println("Signed UP: " + cafeAPI.users().signUp("beanbeanjuice10", "password10"));
    }

}
