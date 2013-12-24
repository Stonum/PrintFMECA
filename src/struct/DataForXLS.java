/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package struct;

import frames.ComponentsNode;
import frames.ComponentsRootCauses;

/**
 *
 * @author Stonum
 */
public class DataForXLS {

    public String B;
    public String C;
    public String D;
    public String E;
    public String F;
    public String G;
    public String H;
    public String I;
    public String J;
    public String K;
    public String L;
    public Integer M;
    public Integer N;
    public String O;
    public String P;
    public String Q;
    public String R;
    public String S;
    public String T;
    public String U;
    public Integer V1;
    public String V;

    public DataForXLS(ComponentsNode c1, ComponentsRootCauses c2) {
        B = c1.nameNode;
        C = c1.nodeFunc.getText();
        D = c1.nodeView.getText();
        E = c1.nodeAftermath.getText();
        F = c2.c1.getSelectedItem().toString();
        G = c2.namePotencialCause;
        H = c2.c2.getSelectedItem().toString();
        I = c2.c3.getSelectedItem().toString();
        J = c2.t4.getText();
        K = c2.t5.getText();
        L = c2.c6.getSelectedItem().toString();
        M = Integer.parseInt(F) * Integer.parseInt(H);
        N = Integer.parseInt(F) * Integer.parseInt(H) * Integer.parseInt(L);

        O = c2.t7.getText();
        P = c2.t8.getText();
        Q = c2.t9.getText();
        R = c2.t10.getText();

        S = c2.c11.getSelectedItem().toString();

        T = c2.c12.getSelectedItem().toString();

        U = c2.c13.getSelectedItem().toString();
        V1 = Integer.parseInt(S) * Integer.parseInt(T) * Integer.parseInt(U);
        if (V1 == 0) {
            V = "";
        } else {
            V = V1.toString();
        }

    }
}
