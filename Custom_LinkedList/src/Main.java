public class Main {
    public static void main(String[] args) {
        CustomLinkedList<String> list = new CustomLinkedList<>();
        list.add("Java");
        list.add("Python");
        list.add("C++");
        System.out.println("List contains 'Python': " + list.contains("Python"));
        for (String lang : list) {
            System.out.println("Language: " + lang);
        }
        list.remove("Python");
        System.out.println("\nAfter removal:");
        for (String lang : list) {
            System.out.println("Language: " + lang);
        }

        System.out.println("Size of list: " + list.size());
    }
}