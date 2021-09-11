package com.gyangod.service;

import com.gyangod.entity.CustomerEntity;
import com.gyangod.enums.UserStatusEvents;
import com.gyangod.enums.UserStatusState;
import com.gyangod.repository.CustomerRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

import static com.gyangod.constants.StateMachineConstant.CUSTOMER_STATE_MACHINE_HEADER;

public class CustomerStateMachineBuilder {


    /**
     *
     * #To write in the database if there is any state changed in any Package Entity
     * @return The State machine build by it
     */
    static StateMachine<UserStatusState, UserStatusEvents> buildUserStateMachine(CustomerEntity customerEntity, CustomerRepository customerRepository,
                                                                                 StateMachineFactory<UserStatusState, UserStatusEvents> stateUserStatusEventsStateMachineFactory){
        StateMachine<UserStatusState, UserStatusEvents> sm = stateUserStatusEventsStateMachineFactory.getStateMachine(customerEntity.getCustomerId());
        sm.stop();
        sm.getStateMachineAccessor().doWithAllRegions(packageStatePackageEventsStateMachineAccess -> {
            packageStatePackageEventsStateMachineAccess.addStateMachineInterceptor(new StateMachineInterceptorAdapter<>() {

                @Override
                public void preStateChange(State state, Message message, Transition transition, StateMachine stateMachine) {
                    Optional.ofNullable(message).ifPresent(msg-> Optional.ofNullable(msg.getHeaders().getOrDefault(CUSTOMER_STATE_MACHINE_HEADER,"default"))
                            .ifPresent(packageId -> {
                                customerEntity.setUserStatus((UserStatusState) state.getId());
                                customerRepository.save(customerEntity);
                            }));
                }
            });
            packageStatePackageEventsStateMachineAccess.resetStateMachine(
                    new DefaultStateMachineContext<>(customerEntity.getUserStatus(),null,null,null));
        });
        sm.start();
        return sm;
    }

    /**
     * 
     * @param customerEntity take the customer object
     * @param customerRepository need the current autowired repository
     * @param events need the event that need to pass
     * @param userStatusEventsStateMachineFactory need stateMachine factory
     */
    public static void sendMessageToStateMachine(CustomerEntity customerEntity, CustomerRepository customerRepository,UserStatusEvents  events,
                                                 StateMachineFactory<UserStatusState, UserStatusEvents> userStatusEventsStateMachineFactory){
        Message<UserStatusEvents> packageEventsMessage = MessageBuilder.withPayload(events).setHeader(CUSTOMER_STATE_MACHINE_HEADER,customerEntity.getUserStatus()).build();
        buildUserStateMachine(customerEntity,customerRepository,userStatusEventsStateMachineFactory).sendEvent(packageEventsMessage);
    }

}
