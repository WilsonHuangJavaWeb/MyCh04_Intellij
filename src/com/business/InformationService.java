package com.business;

import com.dao.InformationDAO;
import com.persistence.Information;

import java.util.List;

/**
 * Created by ki264 on 2017/3/25.
 */
public class InformationService {
    private InformationDAO informationDAO;

    public InformationService() {
        this.informationDAO = new InformationDAO();
    }

    public void addOrUpdateInfo(Information information) {
        if (information == null)
            return;
        informationDAO.makePersistent(information);
    }

    public Information getInfoByID(String id) {
        if (id == null) {
            return null;
        }
        return informationDAO.getById(id);
    }

    public List<Information> getAllInfo() {
        return informationDAO.getAll();
    }

    public void deleteInfo(Information information) {
        if (information == null) {
            return;
        }
        if (information.getId() == null) {
            return;
        }
        informationDAO.makePersistent(information);
    }

    public void deleteInfoById(String id) {
        Information information = informationDAO.getById(id);
        informationDAO.makePersistent(information);
    }


}
