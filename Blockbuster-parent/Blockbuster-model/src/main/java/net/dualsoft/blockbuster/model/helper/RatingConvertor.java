/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.helper;

import net.dualsoft.blockbuster.db.jooq.enums.MpaaRating;


public class RatingConvertor {

    public static MpaaRating StringToRating(String rating) {
        switch (rating) {
            case "PG":
                return MpaaRating.PG;
            case "PG-13":
                return MpaaRating.PG_13;
            case "G":
                return MpaaRating.G;
            case "R":
                return MpaaRating.R;
            case "NC-17":
                return MpaaRating.NC_17;
            default:
                return MpaaRating.PG;
        }
    }
}
