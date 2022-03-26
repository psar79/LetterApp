package wzorceprojektowe.builder;

import java.util.Arrays;

public class Start {
    public static void main(String[] args) {
        DocumentItem documentItem = DocumentItem
                .builder()
                .title("Contract between X and Y")
                .category(DocumentCategory.of("Contract"))
                .signature(Arrays.asList("Kowalski", "Nowak"))
                .build();
        System.out.println(documentItem);
    }
}
