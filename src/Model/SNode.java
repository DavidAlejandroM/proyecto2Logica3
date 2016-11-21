package Model;

public class SNode {

    private SNode link;
    private Object data;

    public SNode(Object data) {
        this.data = data;
        this.link = null;
    }

    public SNode getLink() {
        return link;
    }

    public Object getData() {
        return data;
    }

    public void setLink(SNode link) {
        this.link = link;
    }

    public void setData(String data) {
        this.data = data;
    }

}
