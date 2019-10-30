package com.gyangod.config;

import com.gyangod.enums.PackageEvents;
import com.gyangod.enums.PackageState;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachineFactory
public class PackageStateMachine extends StateMachineConfigurerAdapter<PackageState, PackageEvents> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<PackageState, PackageEvents> config) throws Exception {
        config.withConfiguration().autoStartup(false).listener(new StateMachineListenerAdapter<PackageState, PackageEvents>() {
            @Override
            public void stateChanged(State<PackageState, PackageEvents> from, State<PackageState, PackageEvents> to) {
                //TODO: Log it while the state is changed.
                super.stateChanged(from, to);
            }
        });
    }

    @Override
    public void configure(StateMachineStateConfigurer<PackageState, PackageEvents> states) throws Exception {
        states.withStates().initial(PackageState.ACTIVE).stateEntry(PackageState.ACTIVE, stateContext -> {
            stateContext.getExtendedState().getVariables().getOrDefault("packageId", "default");
            //TODO: Log this activity
        }).state(PackageState.INACTIVE).state(PackageState.STUDENT).state(PackageState.TEACHER)
                .end(PackageState.DEPRECIATE);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<PackageState, PackageEvents> transitions) throws Exception {
        transitions
                .withExternal().source(PackageState.ACTIVE).target(PackageState.STUDENT).event(PackageEvents.student).and()
                .withExternal().source(PackageState.ACTIVE).target(PackageState.TEACHER).event(PackageEvents.teacher).and()
                .withExternal().source(PackageState.STUDENT).target(PackageState.TEACHER).event(PackageEvents.change).and()
                .withExternal().source(PackageState.TEACHER).target(PackageState.STUDENT).event(PackageEvents.change).and()
                .withExternal().source(PackageState.STUDENT).target(PackageState.INACTIVE).event(PackageEvents.deactivate).and()
                .withExternal().source(PackageState.TEACHER).target(PackageState.INACTIVE).event(PackageEvents.deactivate).and()
                .withExternal().source(PackageState.INACTIVE).target(PackageState.STUDENT).event(PackageEvents.student).and()
                .withExternal().source(PackageState.INACTIVE).target(PackageState.TEACHER).event(PackageEvents.teacher).and()
                .withExternal().source(PackageState.STUDENT).target(PackageState.DEPRECIATE).event(PackageEvents.destroy).and()
                .withExternal().source(PackageState.TEACHER).target(PackageState.DEPRECIATE).event(PackageEvents.destroy).and()
                .withExternal().source(PackageState.INACTIVE).target(PackageState.DEPRECIATE).event(PackageEvents.destroy);
    }
}
