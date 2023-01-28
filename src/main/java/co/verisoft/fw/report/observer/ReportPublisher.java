package co.verisoft.fw.report.observer;

/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import lombok.Synchronized;

import java.util.ArrayList;
import java.util.List;


/**
 * Implementation of the publisher (AKA subject) of the report observer mechanism. It is implemented as a singleton
 *
 * @author <a href="mailto:nir@verisoft.co">Nir Gallner</a>
 * @since 0.0.5 (Jan 2023)
 */
public final class ReportPublisher implements Publisher {

    private static ReportPublisher instance;
    private final List<Observer> observers;


    private ReportPublisher() {
        observers = new ArrayList<>();
    }


    @Synchronized
    public static ReportPublisher getInstance() {
        if (instance == null)
            instance = new ReportPublisher();

        return instance;
    }


    @Override
    public void register(Observer newObserver) {
        int observerIndex = observers.indexOf(newObserver);
        if (observerIndex >= 0) // Avoid duplication. Will only work if THE SAME OBJECT wants to register again
            return;

        this.observers.add(newObserver);
    }


    @Override
    public void unregister(Observer deleteObserver) {
        int observerIndex = observers.indexOf(deleteObserver);
        this.observers.remove(observerIndex);
    }


    @Override
    public void notifyObserver(ReportEntry instance) {
        for (Observer observer : observers)
            observer.update(instance);
    }
}
