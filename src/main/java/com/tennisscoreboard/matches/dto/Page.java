package com.tennisscoreboard.matches.dto;

import com.tennisscoreboard.matches.Match;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Page {
    private final long pagesQuantity;
    private final String filterName;
    private final Long matchesQuantity;
    private final List<Match> matches;

//    public static class Builder {
//        private long pagesQuantity = 1;
//        private String filterName = "";
//        private long matchesQuantity;
//        private List<Match> matches;
//
//        public Builder() {
//
//        }
//        public Builder pagesQuantity(long value) {
//            this.pagesQuantity = value;
//            return this;
//        }
//
//        public Builder matchesQuantity(long value) {
//            this.matchesQuantity = value;
//            return this;
//        }
//
//        public Builder matches(List<Match> value) {
//            this.matches = value;
//            return this;
//        }
//
//        public Builder filterName(String value) {
//            this.filterName = value;
//            return this;
//        }
//
//        public Page build() {
//            return new Page(this);
//        }
//    }
//
//    public Page(Builder builder) {
//          pagesQuantity = builder.pagesQuantity;
//          filterName = builder.filterName;
//          matchesQuantity = builder.matchesQuantity;
//          matches = builder.matches;
//    }
//
//    public long getPagesQuantity() {
//        return pagesQuantity;
//    }
//    public String getFilterName() {
//        return filterName;
//    }
//    public long getMatchesQuantity() {
//        return matchesQuantity;
//    }
//    public List<Match> getMatches() {
//        return matches;
//    }
}
