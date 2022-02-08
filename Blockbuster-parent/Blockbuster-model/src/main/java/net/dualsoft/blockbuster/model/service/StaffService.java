/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.ArrayList;
import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Staff;
import net.dualsoft.blockbuster.model.DTO.StaffView;
import net.dualsoft.blockbuster.model.helper.pojos.Response;

/**
 *
 * @author teodora
 */
public interface StaffService {
    
    public Response<List<Staff>> getStaff();
    
    void changeStoreId(int staffId, int storeId);
    
    public List<StaffView> getStaffForStore(Integer storeId);
    
    public StaffView getStaffWithId(Integer staffId);
}
