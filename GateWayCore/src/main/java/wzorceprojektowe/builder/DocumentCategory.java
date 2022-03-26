package wzorceprojektowe.builder;

public class DocumentCategory {
    private String name;

private DocumentCategory(String name){
    this.name = name;
}
static  DocumentCategory of(String name){
    return new DocumentCategory(name);
}

    @Override
    public String toString() {
        return "DocumentCategory{" +
                "name='" + name + '\'' +
                '}';
    }
}
