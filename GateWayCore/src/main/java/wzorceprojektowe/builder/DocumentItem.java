package wzorceprojektowe.builder;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentItem {
    private final String title;
    private final DocumentCategory category;
    private final List<String> signature;

    public DocumentItem(String title, DocumentCategory category, List<String> signature) {
        this.title = title;
        this.category = category;
        this.signature = signature;
    }

    static  DocumentItemBuilder builder(){
        return new DocumentItemBuilder();
    }

    public String toString() {
        return "DocumentItem(title=" + this.title + ", \ncategory=" +this.category
                + ", \nsignature=" + this.signature.stream()
                .map(String::toString)
                .collect(Collectors.joining(","))+")";
    }
    static class DocumentItemBuilder {
        private String title;
        private DocumentCategory category;
        private List<String> signature;

        DocumentItemBuilder title(String title) {
            this.title = title;
            return this;
        }
        DocumentItemBuilder category(DocumentCategory category){
            this.category = category;
            return this;
        }
        DocumentItemBuilder signature(List<String> signature){
            this.signature = signature;
            return this;
        }
        DocumentItem build(){
            return  new DocumentItem(title,category,signature);
        }
    }
}
