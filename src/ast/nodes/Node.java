package ast.nodes;

public class Node implements CodeGenerate {
    private int line;
    private int col;
    public void setLine(int line) {
            this.line = line;
        }
        public void setCol(int col) {
            this.col = col;
        }

        public int getLine() {
            return line;
        }
        public int getCol() {
            return col;
        }

    @Override
    public String generateCode() {
        return "";
    }
}
