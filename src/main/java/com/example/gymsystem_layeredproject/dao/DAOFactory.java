package com.example.gymsystem_layeredproject.dao;


import com.example.gymsystem_layeredproject.dao.custom.impl.*;



public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public enum DAOTypes {
        EQUIPMENT, MEMBER, PAYMENT, SUPPLEMENT , SUPPLEMENTTRANSACTION , TRAINER , WORKOUTPLAN
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case EQUIPMENT:
                return new EquipmentDAOImpl();
            case MEMBER:
                return new MemberDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
                case SUPPLEMENT:
                    return new SupplementDAOImpl();
                    case SUPPLEMENTTRANSACTION:
                        return new SupplementTransactionDAOImpl();
                        case TRAINER:
                            return new TrainerDAOImpl();
                                  case WORKOUTPLAN:
                                      return new WorkoutPlanDAOImpl();
            default:
                return null;
        }
    }
}
