package org.xflash.edd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xflash.edd.checkers.CollapsingSolutionChecker;
import org.xflash.edd.checkers.GridHeaderSolutionChecker;
import org.xflash.edd.checkers.SolutionChecker;
import org.xflash.edd.checkers.results.CheckResult;
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
        SolutionChecker collapseChecker = new CollapsingSolutionChecker();

        HashMap<Integer, Set<GridSolution>> map = new HashMap<>();


        while (maxNb > 0) {
            int v = maxNb--;
            LOGGER.debug("Building possible solutions for Value : {}", v);
            gridBrowser.forEachValuedPill(v,
                    valuedPill -> {
                        if (map.containsKey(v + 1)) {
                            for (GridSolution gridSolution : map.get(v + 1)) {
                                List<Pill> pills1 = new ArrayList<>(gridSolution.getPills());
                                pills1.add(valuedPill);
                                GridSolution newSolution = new GridSolution(pills1);
                                CheckResult checkResult = collapseChecker.check(newSolution);
                                if (checkResult == null) {
                                    if (!map.containsKey(v))
                                        map.put(v, new HashSet<>());
                                    map.get(v).add(newSolution);
                                }
                            }
                        } else {
                            if (!map.containsKey(v))
                                map.put(v, new HashSet<>());
                            map.get(v).add(new GridSolution(Arrays.asList(valuedPill)));
                        }


                    }
            );
            LOGGER.debug("Founded  possible solutions for Value : {} = {}", v, map.get(v).size());

        }

        HashSet<GridSolution> sols = new HashSet<>();
        Set<GridSolution> possibleSolutions = map.get(1);
        LOGGER.debug("Checking all final possible solutions {}", possibleSolutions.size());
        for (GridSolution gridSolution : possibleSolutions) {
            if (gridHeaderSolutionChecker.check(gridSolution) == null) {
                sols.add(gridSolution);
            }
        }

        LOGGER.debug("Solutions {}", sols);

        return sols;
    }


}
