public class Calculator
{
    public enum CalcModes
    {
        add, minus, multiplication, division, squareRoot, sin, cos, tan, allClear, result
    }

    public enum InputState
    {
        first, second, full
    }

    private double firstNum, secoundNum;


    public void init()
    {
        firstNum = -1;
        secoundNum = -1;
    }

    public double Calc(CalcModes mode)
    {
        if (CheckState() == InputState.full)
        {
            switch (mode)
            {
                case add:
                    return firstNum + secoundNum;
                case minus:
                    return firstNum - secoundNum;
                case multiplication:
                    return  firstNum * secoundNum;
                case division:
                    return firstNum / secoundNum;
                case squareRoot:
                    break;
                case sin:
                    break;
                case cos:
                    break;
                case tan:
                    break;
            }
        }
        return 0;
    }

    public void SetNum(double num)
    {
        if (CheckState() == InputState.first) firstNum = num;
        else secoundNum = num;
    }

    public void SetNum(double num, InputState state)
    {
        switch (state)
        {
            case InputState.first:
                firstNum = num;
                break;
            case InputState.second:
                secoundNum = num;
                break;
        }
    }


    public InputState CheckState()
    {
        if (firstNum == -1) return InputState.first;
        else if (secoundNum == -1) return InputState.second;
        else return InputState.full;
    }

    public boolean CheckDivisionByZero()
    {
        if (firstNum == 0) return true;
        else return false;
    }
}
