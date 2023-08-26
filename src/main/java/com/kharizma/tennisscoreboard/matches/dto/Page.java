package com.kharizma.tennisscoreboard.matches.dto;

import com.kharizma.tennisscoreboard.matches.Match;

import java.util.List;

public class Page {

    private long pagesQuantity;
    private String filterName;
    private Long matchesQuantity;
    private List<Match> matches;

    public static class Builder {
        private long pagesQuantity = 1;
        private String filterName = "";
        private long matchesQuantity;
        private List<Match> matches;

        public Builder() {

        }
        public Builder pagesQuantity(long value) {
            this.pagesQuantity = value;
            return this;
        }

        public Builder matchesQuantity(long value) {
            this.matchesQuantity = value;
            return this;
        }

        public Builder matches(List<Match> value) {
            this.matches = value;
            return this;
        }

        public Builder filterName(String value) {
            this.filterName = value;
            return this;
        }

        public Page build() {
            return new Page(this);
        }
    }

    public Page(Builder builder) {
          pagesQuantity = builder.pagesQuantity;
          filterName = builder.filterName;
          matchesQuantity = builder.matchesQuantity;
          matches = builder.matches;
    }

    public long getPagesQuantity() {
        return pagesQuantity;
    }
    public String getFilterName() {
        return filterName;
    }
    public long getMatchesQuantity() {
        return matchesQuantity;
    }
    public List<Match> getMatches() {
        return matches;
    }
}
