package predictive;

import java.util.LinkedHashSet;
import java.util.Set;

public class TreeNode {
    private final TreeNode[] children;
    private final Set<String> words;

    /**
     * Constructor which creates an array of children
     * and an empty LinkedHashSet.
     */
    TreeNode(){
        children = new TreeNode[8];
        this.words = new LinkedHashSet<>();
    }

    public TreeNode[] getChildren() {
        return children;
    }

    public void addWord(String word) {
        words.add(word);
    }

    public Set<String> getWords() {
        return words;
    }

}
