package com.example.gymsystem_layeredproject.bo;

import com.example.gymsystem_layeredproject.bo.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        EQUIPMENT, MEMBER, PAYMENT, SUPPLEMENT , SUPPLEMENTTRANSACTION , TRAINER , WORKOUTPLAN
    }

    public SuperBO getBO(BOTypes type) {
        switch (type) {
            case EQUIPMENT:
                return new EquipmentBOImpl();
            case MEMBER:
                return new MemberBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
                case SUPPLEMENT:
                    return new SupplementBOImpl();
                    case SUPPLEMENTTRANSACTION:
                        return new SupplementTransactionBOImpl();
                        case TRAINER:
                            return new TrainerBOImpl();
                                  case WORKOUTPLAN:
                                      return new WorkoutPlanBOImpl();
            default:
                return null;
        }
    }
}
