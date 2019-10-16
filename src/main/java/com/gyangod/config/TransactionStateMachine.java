package com.gyangod.config;

import com.gyangod.enums.TransactionEvents;
import com.gyangod.enums.TransactionState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class TransactionStateMachine extends StateMachineConfigurerAdapter<TransactionState,TransactionEvents> {

    @Override
    public void configure(StateMachineTransitionConfigurer<TransactionState, TransactionEvents> transitions) throws Exception {
        transitions
                .withExternal().source(TransactionState.PAID).target(TransactionState.ATTENDED).event(TransactionEvents.attend)
                .and()
                .withExternal().source(TransactionState.PAID).target(TransactionState.NOT_ATTENDED).event(TransactionEvents.no_show)
                .and()
                .withExternal().source(TransactionState.ATTENDED).target(TransactionState.SETTLED).event(TransactionEvents.settle)
                .and()
                .withExternal().source(TransactionState.NOT_ATTENDED).target(TransactionState.REFUNDED).event(TransactionEvents.refund)
                .and()
                .withExternal().source(TransactionState.SETTLED).target(TransactionState.CALCULATED).event(TransactionEvents.calculate);
    }

    @Override
    public void configure(StateMachineStateConfigurer<TransactionState, TransactionEvents> states) throws Exception {
        states.withStates().initial(TransactionState.PAID)
                .state(TransactionState.ATTENDED).state(TransactionState.NOT_ATTENDED).state(TransactionState.SETTLED)
                .end(TransactionState.REFUNDED).end(TransactionState.CALCULATED);
    }

    @Override
    public void configure(StateMachineConfigurationConfigurer<TransactionState, TransactionEvents> config) throws Exception {
        StateMachineListenerAdapter<TransactionState, TransactionEvents> adapter = new StateMachineListenerAdapter<TransactionState, TransactionEvents>() {
            @Override
            public void stateChanged(State<TransactionState, TransactionEvents> from, State<TransactionState, TransactionEvents> to) {
                super.stateChanged(from, to);
            }
        };
        config.withConfiguration().autoStartup(false).listener(adapter);
    }
}
