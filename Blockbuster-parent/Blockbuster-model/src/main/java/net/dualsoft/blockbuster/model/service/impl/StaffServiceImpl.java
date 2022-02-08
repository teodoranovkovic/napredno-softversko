/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import static net.dualsoft.blockbuster.db.jooq.Tables.STAFF;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.Staff;
import net.dualsoft.blockbuster.model.DTO.StaffView;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.StaffService;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class StaffServiceImpl implements StaffService{
    
    @Autowired
    private DSLContext context;

    @Override
    public Response<List<Staff>> getStaff() {
        
        List<Staff> staff = context
                .selectFrom(STAFF)
                .fetchInto(Staff.class);
        
        if(staff.isEmpty()){
            return new Response(null, "OK", 200);
        }
        
        return new Response(staff,"OK",200);
    }

    @Override
    public void changeStoreId(int staffId, int storeId) {
        context.update(STAFF)
                .set(STAFF.STORE_ID,(short) storeId)
                .where(STAFF.STAFF_ID.eq(staffId))
                .execute();
    }

    @Override
    public List<StaffView> getStaffForStore(Integer storeId) {
        List<StaffView> staff = context
                .select(STAFF.STAFF_ID,STAFF.FIRST_NAME,STAFF.LAST_NAME,STAFF.EMAIL,STAFF.USERNAME)
                .from(STAFF)
                .where(STAFF.STORE_ID.eq(storeId.shortValue()))
                .fetchInto(StaffView.class);
        
        return staff;
    }

    @Override
    public StaffView getStaffWithId(Integer staffId) {
        StaffView staff = context
                .select(STAFF.STAFF_ID,STAFF.FIRST_NAME,STAFF.LAST_NAME,STAFF.EMAIL,STAFF.USERNAME)
                .from(STAFF)
                .where(STAFF.STAFF_ID.eq(staffId))
                .fetchSingleInto(StaffView.class);
        
        return staff;
    }
    
    
}
