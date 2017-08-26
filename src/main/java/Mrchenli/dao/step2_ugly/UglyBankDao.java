package Mrchenli.dao.step2_ugly;

import Mrchenli.dao.AbstractBankDao;

public class UglyBankDao extends AbstractBankDao {

    //传递的connection当然不希望关掉
    @Override
    public boolean isConnectionClose() {
        return false;
    }
}
