package com.redstoneblocks.java.swing_mvc.util;

import org.reflections.Configuration;
import org.reflections.Reflections;
import org.reflections.Store;
import org.reflections.scanners.Scanner;
import org.reflections.serializers.Serializer;
import org.reflections.util.QueryFunction;

import java.io.File;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public interface ReflectionsService {
    Reflections collect(InputStream inputStream, Serializer serializer);

    Reflections collect(File file, Serializer serializer);

    Reflections merge(Reflections reflections);

    void expandSuperTypes(Map<String, Set<String>> subTypesStore, Map<String, Set<String>> typesAnnotatedStore);

    <T> Set<T> get(QueryFunction<Store, T> query);

    <T> Set<Class<? extends T>> getSubTypesOf(Class<T> type);

    Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation);

    Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotation, boolean honorInherited);

    Set<Class<?>> getTypesAnnotatedWith(Annotation annotation);

    Set<Class<?>> getTypesAnnotatedWith(Annotation annotation, boolean honorInherited);

    Set<Method> getMethodsAnnotatedWith(Class<? extends Annotation> annotation);

    Set<Method> getMethodsAnnotatedWith(Annotation annotation);

    Set<Method> getMethodsWithSignature(Class<?>... types);

    Set<Method> getMethodsWithParameter(AnnotatedElement type);

    Set<Method> getMethodsReturn(Class<?> type);

    @SuppressWarnings("rawtypes")
    Set<Constructor> getConstructorsAnnotatedWith(Class<? extends Annotation> annotation);

    @SuppressWarnings("rawtypes")
    Set<Constructor> getConstructorsAnnotatedWith(Annotation annotation);

    @SuppressWarnings("rawtypes")
    Set<Constructor> getConstructorsWithSignature(Class<?>... types);

    @SuppressWarnings("rawtypes")
    Set<Constructor> getConstructorsWithParameter(AnnotatedElement type);

    Set<Field> getFieldsAnnotatedWith(Class<? extends Annotation> annotation);

    Set<Field> getFieldsAnnotatedWith(Annotation annotation);

    Set<String> getResources(String pattern);

    Set<String> getResources(Pattern pattern);

    List<String> getMemberParameterNames(Member member);

    Collection<Member> getMemberUsage(Member member);

    Set<String> getAllTypes();

    Set<String> getAll(Scanner scanner);

    Store getStore();

    Configuration getConfiguration();

    File save(String filename);

    File save(String filename, Serializer serializer);
}
