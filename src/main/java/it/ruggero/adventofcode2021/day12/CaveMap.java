package it.ruggero.adventofcode2021.day12;

import it.ruggero.adventofcode2021.day12.entity.Cave;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class CaveMap {
    private Map<String, Cave> caves = new HashMap<>();
    private List<Path> paths = new ArrayList<>();


    public CaveMap(String filePath) {
        try (Scanner input = new Scanner(new File(filePath))) {
            while (input.hasNextLine()) {
                String[] inputs = input.nextLine().split("-");
                Cave newCave = new Cave(inputs[0]);
                Cave destinationCave = new Cave(inputs[1]);
                if (!caves.containsKey(inputs[0])) {
                    caves.put(inputs[0], newCave);

                } else {
                    newCave = caves.get(inputs[0]);

                }

                if (!caves.containsKey(inputs[1])) {
                    caves.put(inputs[1], destinationCave);

                } else {
                    destinationCave = caves.get(inputs[1]);
                }
                newCave.getNodes().add(destinationCave);
                destinationCave.getNodes().add(newCave);


            }
        } catch (FileNotFoundException
                ex) {
            System.out.println(ex);
        }
    }

    public Map<String, Cave> getCaves() {
        return caves;
    }

    public List<Path> getPaths() {
        return paths;
    }

    public void drawPaths() {
        Cave startCave = caves.get("start");
        Path extendedPath = new Path();

        drawPath(startCave, extendedPath );
        paths.removeIf(path -> path.isToBeDeleted() || !path.isComplete());
        paths = paths.stream().distinct().collect(Collectors.toList());

    }

    public void drawPath(Cave startCave, Path path) {


        if (path.isComplete() || startCave.getNodes().isEmpty()) {
            return;
        }

        boolean proceed = path.add(startCave);
        if ( proceed) {
            for(Cave cave : startCave.getNodes()) {
                Path extendedPath = new Path(path);

                drawPath(cave,extendedPath);
                if(path.isComplete() && !path.isToBeDeleted()) {
                    paths.add(extendedPath);
                }

            }

        }



    }


    public class Path {
        private List<Cave> caves = new ArrayList<>();

        private boolean complete;

        private boolean toBeDeleted;

        public Path(){

        }

        public Path(Path otherPath){
            this.complete = otherPath.complete;
            this.toBeDeleted = otherPath.toBeDeleted;
            this.caves = new ArrayList<>(otherPath.caves);

        }

        public boolean add(Cave cave) {

            if (!cave.getBig()) {
                if (!this.caves.contains(cave)) {
                    caves.add(cave);
                    if (cave.getLabel().equals("end")) {
                        complete = true;
                        toBeDeleted = false;
                    }
                    return true;

                } else {
                    complete = false;
                    toBeDeleted = true;
                    return false;
                }
            } else {
                caves.add(cave);
                return true;
            }


        }

        public List<Cave> getCaves() {
            return caves;
        }

        public boolean isComplete() {
            return complete;
        }

        public void setComplete(boolean complete) {
            this.complete = complete;
        }

        public boolean isToBeDeleted() {
            return toBeDeleted;
        }

        public void setToBeDeleted(boolean toBeDeleted) {
            this.toBeDeleted = toBeDeleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Path path = (Path) o;

            if (complete != path.complete) return false;
            if (toBeDeleted != path.toBeDeleted) return false;
            return caves != null ? caves.equals(path.caves) : path.caves == null;
        }

        @Override
        public int hashCode() {
            int result = caves != null ? caves.hashCode() : 0;
            result = 31 * result + (complete ? 1 : 0);
            result = 31 * result + (toBeDeleted ? 1 : 0);
            return result;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Path.class.getSimpleName() + "[", "]")
                    .add("caves=" + caves)
                    .add("complete=" + complete)
                    .add("toBeDeleted=" + toBeDeleted)
                    .toString();
        }
    }
}
