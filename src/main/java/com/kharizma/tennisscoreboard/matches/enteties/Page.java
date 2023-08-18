package com.kharizma.tennisscoreboard.matches.enteties;

import com.kharizma.tennisscoreboard.matches.Match;
import com.kharizma.tennisscoreboard.matches.MatchDao;

import java.util.List;

public class Page {

    private long pagesQuantity;
    private String filterName;
    private Long matchesQuantity;
    private List<Match> matches;

    public Page() {
    }

    public long getPagesQuantity() {
        return pagesQuantity;
    }

    public void setPagesQuantity(long pagesQuantity) {
        this.pagesQuantity = pagesQuantity;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public Long getMatchesQuantity() {
        return matchesQuantity;
    }

    public void setMatchesQuantity(Long matchesQuantity) {
        this.matchesQuantity = matchesQuantity;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
