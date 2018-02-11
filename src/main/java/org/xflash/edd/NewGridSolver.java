package org.xflash.edd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xflash.edd.checkers.CollapsingSolutionChecker;
import org.xflash.edd.checkers.GridHeaderMinSolutionChecker;
import org.xflash.edd.checkers.GridHeaderSolutionChecker;
import org.xflash.edd.checkers.SolutionChecker;
import org.xflash.edd.model.Grid;
import org.xflash.edd.model.GridSolution;
import org.xflash.edd.model.Pill;
import org.xflash.edd.solver.GridSolver;

import java.util.*;

public class NewGridSolver implements GridSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NewGridSolver.class);

    @Override
    public Collection<GridSolution> solve(Grid grid, int maxNb) {

        GridBrowser gridBrowser = new GridBrowser(grid);
        Set<Pill> pills = new HashSet<>();

        GridHeaderSolutionChecker gridHeaderSolutionChecker = new GridHeaderSolutionChecker(grid);
        GridHeaderMinSolutionChecker gridHeaderMinSolutionChecker = new GridHeaderMinSolutionChecker(grid);
        SolutionChecker collapseChecker = new CollapsingSolutionChecker();

        HashMap<Integer, Set<GridSolution>> map = new HashMap<>();


        while (maxNb > 0) {
            int v = maxNb--;
            LOGGER.debug("==> Building possible solutions for Value : {}", v);

            gridBrowser.forEachValuedPill(v,
                    valuedPill -> {
                        if (map.containsKey(v + 1)) {
                            Set<GridSolution> previousGridSolutions = map.get(v + 1);
                            LOGGER.debug("Checking if valued pill {} for value {} match with {} grid Solutions of previous value {} ",
                                    valuedPill, v, previousGridSolutions.size(), v + 1);

                            for (GridSolution previousGridSolution : previousGridSolutions) {
                                GridSolution newSolution = new GridSolution(previousGridSolution);
                                newSolution.addPill(valuedPill);

                                if (collapseChecker.check(newSolution) != null) {
                                    LOGGER.debug("{} has some overlaps ", newSolution);
                                } else if (gridHeaderMinSolutionChecker.check(newSolution) != null) {
                                    LOGGER.debug("{} don't fit hint values", newSolution);
                                } else {
                                    LOGGER.debug("=> Adding a possible {} for value {}", newSolution, v);
                                    if (!map.containsKey(v)) map.put(v, new HashSet<>());
                                    map.get(v).add(newSolution);
                                }

                            }

                        } else {
                            GridSolution newSolution = new GridSolution();
                            newSolution.addPill(valuedPill);
                            if (gridHeaderMinSolutionChecker.check(newSolution) == null) {
                                LOGGER.debug("=> Adding a possible {} for value {}", newSolution, v);
                                if (!map.containsKey(v))
                                    map.put(v, new HashSet<>());
                                map.get(v).add(newSolution);
                            } else {
                                LOGGER.debug("{} don't fit hint values", newSolution);
                            }
                        }
                    }
            );

            if (map.get(v) == null) {
                LOGGER.info("No solutions remains after value {}", v);
                return Collections.emptyList();
            }

            LOGGER.debug("Possible solutions for Value : {} = {}", v, map.get(v));

//            if (map.containsKey(v + 1)) {
//                LOGGER.debug("Remove {} possible values for previous value {}", map.get(v + 1).size(), v + 1);
//                map.remove(v + 1);
//            }
        }

        Set<GridSolution> goodSols = new HashSet<>();
        Set<GridSolution> possibleSolutions = map.get(1);
        LOGGER.debug("Checking the {} possible solutions for {}", possibleSolutions.size(), 1);
        for (GridSolution gridSolution : possibleSolutions) {
            if (gridHeaderSolutionChecker.check(gridSolution) == null) {
                goodSols.add(gridSolution);
            }
        }

        LOGGER.debug("Solutions {}", goodSols);

        return goodSols;
    }


}
