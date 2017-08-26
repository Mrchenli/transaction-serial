package Mrchenli.dao.step2_ugly;

import Mrchenli.dao.AbstractInsuranceDao;

public class UglyInsuranceDao extends AbstractInsuranceDao{

    @Override
    public boolean isConnectionClose() {
        return false;
    }
}
