package life;

public class GenerationAlgorithm {
    static Universe getNextGeneration(Universe current) {
        Universe nextGeneration = new Universe(current.size());

        for (int row = 0; row < current.size(); ++row) {
            for (int col = 0; col < current.size(); ++col) {
                final int neighbours = current.getNeighboursCount(row, col);
                final var isLive = neighbours == 3 || neighbours == 2 && current.isLive(row, col);
                nextGeneration.setCell(row, col, isLive);
            }
        }
        return nextGeneration;
    }
}
