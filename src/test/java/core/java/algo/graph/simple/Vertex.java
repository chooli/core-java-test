package core.java.algo.graph.simple;

public class Vertex {
    String label;

    public Vertex(String label) {
        this.label = label;
    }

    public boolean hasLabel(String label) {
        return this.label.equals(label);
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (!(o instanceof Vertex)) return false;
        return this.label.equals(((Vertex)o).label);
    }
}
