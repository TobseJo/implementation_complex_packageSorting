package command;

import sortingStation.ZS;

//SOLID-design: interface-segregation
public interface ICommand {
    void execute(ZS zs);
}
