package com.gyangod.service;

import com.gyangod.entity.PackagesEntity;
import com.gyangod.enums.PackageEvents;
import com.gyangod.enums.PackageState;
import com.gyangod.repository.PackagesRepository;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;

import java.util.Optional;

public class PackageStateMachineBuilder {

    static final String STATE_MACHINE_HEADER = "packageId";

    /**
     *
     * #To write in the database if there is any state changed in any Package Entity
     * @return The State machine build by it
     */
    static StateMachine<PackageState,PackageEvents> buildUserStateMachine(PackagesEntity packagesEntity, PackagesRepository packagesRepository,
                                                                          StateMachineFactory<PackageState,PackageEvents> packageEventsStateMachineFactory){
        StateMachine<PackageState,PackageEvents> sm = packageEventsStateMachineFactory.getStateMachine(packagesEntity.getPackageId());
        sm.stop();
        sm.getStateMachineAccessor().doWithAllRegions(packageStatePackageEventsStateMachineAccess -> {
            packageStatePackageEventsStateMachineAccess.addStateMachineInterceptor(new StateMachineInterceptorAdapter<PackageState,PackageEvents>() {

                @Override
                public void preStateChange(State state, Message message, Transition transition, StateMachine stateMachine) {
                    Optional.ofNullable(message).ifPresent(msg-> Optional.ofNullable(msg.getHeaders().getOrDefault(STATE_MACHINE_HEADER,"default"))
                            .ifPresent(packageId -> {
                                packagesEntity.setPackageStatus((PackageState) state.getId());
                                packagesRepository.save(packagesEntity);
                            }));
                }
            });
            packageStatePackageEventsStateMachineAccess.resetStateMachine(
                    new DefaultStateMachineContext<>(packagesEntity.getPackageStatus(),null,null,null));
        });
        sm.start();
        return sm;
    }

    /**
     *
     * @param packagesEntity    which package Entity is going to change the State
     * @param packagesRepository   repository of the package to connect
     * @param events    activate,deactivate,change,destroy
     * @param packageEventsStateMachineFactory  State Machine Factory for the Current Object
     * #To send a Message to the State Machine for a Package Entity
     */
    public static void sendMessageToStateMachine(PackagesEntity packagesEntity, PackagesRepository packagesRepository,PackageEvents  events,
                                          StateMachineFactory<PackageState,PackageEvents> packageEventsStateMachineFactory){
        Message<PackageEvents> packageEventsMessage = MessageBuilder.withPayload(events).setHeader(STATE_MACHINE_HEADER,packagesEntity.getPackageId()).build();
        buildUserStateMachine(packagesEntity,packagesRepository,packageEventsStateMachineFactory).sendEvent(packageEventsMessage);
    }

}
