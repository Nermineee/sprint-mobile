/**
 * Copyright (C) 2009 - 2013 SC 4ViewSoft SRL
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.swr.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer;
import com.codename1.charts.renderers.XYMultipleSeriesRenderer.Orientation;
import com.codename1.charts.renderers.XYSeriesRenderer;
import com.codename1.charts.views.BarChart;
import com.codename1.charts.views.BarChart.Type;
import com.codename1.ui.Form;
import java.util.ArrayList;
import java.util.List;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.esprit.swr.entities.Housing;
import com.esprit.swr.services.ServiceHousing;

/**
 * Sales demo bar chart.
 */
public class SalesBarChart extends AbstractDemoChart {

    /**
     * Returns the chart name.
     *
     * @return the chart name
     */
    public String getName() {
        return "Sales horizontal bar chart";
    }

    /**
     * Returns the chart description.
     *
     * @return the chart description
     */
    public String getDesc() {
        return "The monthly sales for the last 2 years (horizontal bar chart)";
    }

    public Component getChartModelEditor() {
        return null;
    }

    public String getChartTitle() {
        return "";
    }

    public Component execute() {
        String[] titles = new String[]{"Nb Residents", "Capacity"};
        List<double[]> values = new ArrayList<double[]>();
        ArrayList<Housing> LH= ServiceHousing.getInstance().getAllHousings();
        double[] nbr= new double[LH.size()];
        double[] cap= new double[LH.size()];
        int[] colors = new int[]{ColorUtil.LTGRAY, ColorUtil.GRAY};
        XYMultipleSeriesRenderer renderer = buildBarRenderer(colors);
        int j=0;
        for (Housing h : LH) {
        nbr[j]=h.getNbresidents();
        cap[j]=h.getCapacity();
        renderer.addXTextLabel(j+1,h.getName());
        
        j++;
        
        
        
        }
        
        values.add(nbr);
        values.add(cap);
        
       /* values.add(new double[]{5230, 7300, 9240, 10540, 7900, 9200, 12030, 11200, 9500, 10500,
            11600, 13500});
        values.add(new double[]{14230, 12300, 14240, 15244, 15900, 19200, 22030, 21200, 19500, 15500,
            12600, 14000}); */
        
        
        renderer.setOrientation(Orientation.HORIZONTAL);
        
        renderer.setBarSpacing(1);
        renderer.setBarWidth(25);
        renderer.setXLabelsAngle(90);
        
        
        setChartSettings(renderer, "Housings", "", "", 0,
                10, 0, 150, ColorUtil.GRAY, ColorUtil.LTGRAY);
       
       
        initRendererer(renderer);
        int length = renderer.getSeriesRendererCount();
        for (int i = 0; i < length; i++) {
            XYSeriesRenderer seriesRenderer = (XYSeriesRenderer) renderer.getSeriesRendererAt(i);
            seriesRenderer.setDisplayChartValues(true);
        }

        BarChart chart = new BarChart(buildBarDataset(titles, values), renderer,
                Type.DEFAULT);
        return newChart(chart);

    }

}
