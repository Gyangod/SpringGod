package com.gyangod.config;

import com.gyangod.enums.statemachine.UserStatusEvents;
import com.gyangod.enums.statemachine.UserStatusState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import static com.gyangod.constants.StateMachineConstant.CUSTOMER_STATE_MACHINE_HEADER;

@Configuration
public class UserStateMachine extends StateMachineConfigurerAdapter<UserStatusState, UserStatusEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<UserStatusState, UserStatusEvents> config) throws Exception {
        config.withConfiguration().autoStartup(false).listener(new StateMachineListenerAdapter<>() {
            @Override
            public void stateChanged(State<UserStatusState, UserStatusEvents> from, State<UserStatusState, UserStatusEvents> to) {
                //TODO: Log it while the state is changed.
                super.stateChanged(from, to);
            }
        });
    }

    @Override
    public void configure(StateMachineStateConfigurer<UserStatusState, UserStatusEvents> states) throws Exception {
        states.withStates().initial(UserStatusState.ACTIVE).stateEntry(UserStatusState.ACTIVE, stateContext -> {
                    stateContext.getExtendedState().getVariables().getOrDefault(CUSTOMER_STATE_MACHINE_HEADER, "default");
                    //TODO: Log this activity
                }).state(UserStatusState.INACTIVE).state(UserStatusState.LOCKED)
                .end(UserStatusState.EXPIRED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<UserStatusState, UserStatusEvents> transitions) throws Exception {
        transitions
                .withExternal().source(UserStatusState.ACTIVE).target(UserStatusState.INACTIVE).event(UserStatusEvents.deactivate).and()
                .withExternal().source(UserStatusState.ACTIVE).target(UserStatusState.LOCKED).event(UserStatusEvents.lock).and()
                .withExternal().source(UserStatusState.ACTIVE).target(UserStatusState.EXPIRED).event(UserStatusEvents.dead).and()

                .withExternal().source(UserStatusState.EXPIRED).target(UserStatusState.LOCKED).event(UserStatusEvents.lock).and()

                .withExternal().source(UserStatusState.LOCKED).target(UserStatusState.ACTIVE).event(UserStatusEvents.activate).and()
                .withExternal().source(UserStatusState.LOCKED).target(UserStatusState.EXPIRED).event(UserStatusEvents.dead).and()

                .withExternal().source(UserStatusState.INACTIVE).target(UserStatusState.ACTIVE).event(UserStatusEvents.activate).and()
                .withExternal().source(UserStatusState.INACTIVE).target(UserStatusState.EXPIRED).event(UserStatusEvents.dead).and()
                .withExternal().source(UserStatusState.INACTIVE).target(UserStatusState.LOCKED).event(UserStatusEvents.lock);
    }
}
